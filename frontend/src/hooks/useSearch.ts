import { useEffect, useState } from "react";
import { searchAll } from "../api/searchApi";
import { SearchResult } from "../types/search";
import { useDebounce } from "./useDebounce";

interface UseSearchState {
  data: SearchResult | null;
  loading: boolean;
  error: string | null;
}

// Centralizes loading/error state so components never touch axios directly —
// if we later swap this for React Query, only this hook changes.
export function useSearch(query: string) {
  const debouncedQuery = useDebounce(query, 400);
  const [state, setState] = useState<UseSearchState>({ data: null, loading: false, error: null });

  useEffect(() => {
    if (!debouncedQuery.trim()) {
      setState({ data: null, loading: false, error: null });
      return;
    }

    let cancelled = false;
    setState((prev) => ({ ...prev, loading: true, error: null }));

    searchAll(debouncedQuery)
      .then((data) => {
        if (!cancelled) setState({ data, loading: false, error: null });
      })
      .catch((err) => {
        const backendMessage = err.response?.data?.message;
        console.error("Search API Error:", err.response?.data || err.message);
        if (!cancelled) setState({ data: null, loading: false, error: backendMessage || "Something went wrong. Try again." });
      });

    return () => {
      cancelled = true;
    };
  }, [debouncedQuery]);

  return state;
}
