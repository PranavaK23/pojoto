import { Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import SearchResultsPage from "./pages/SearchResultsPage";

function App() {
  return (
    <MainLayout>
      <Routes>
        {/* Homepage arrives in a later step — search page is the working entry point for now */}
        <Route path="/" element={<SearchResultsPage />} />
        <Route path="/search" element={<SearchResultsPage />} />
      </Routes>
    </MainLayout>
  );
}

export default App;
