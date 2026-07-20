interface SearchBarProps {
  value: string;
  onChange: (value: string) => void;
}

export default function SearchBar({ value, onChange }: SearchBarProps) {
  return (
    <input
      type="text"
      value={value}
      onChange={(e) => onChange(e.target.value)}
      placeholder="Search movies, TV shows, games..."
      className="w-full rounded-2xl border border-white/10 bg-surface-card px-6 py-4 text-lg
                 outline-none placeholder:text-white/40 focus:border-indigo-400/60
                 focus:ring-2 focus:ring-indigo-400/20 transition-colors"
    />
  );
}
