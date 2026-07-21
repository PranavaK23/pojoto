import { Link, useLocation } from "react-router-dom";

export default function Navbar() {
  const location = useLocation();

  const navLinks = [
    { name: "Home", path: "/" },
    { name: "Projects", path: "/projects" },
    { name: "YouTube Channel", path: "http://www.youtube.com/@PojoGalaxy", external: true },
  ];

  return (
    <nav className="sticky top-0 z-50 border-b border-gray-200 bg-white/90 backdrop-blur-md">
      <div className="mx-auto flex max-w-6xl items-center justify-between px-6 py-4">
        {/* Logo */}
        <Link to="/" className="block h-[44px] w-auto transition-transform hover:scale-105">
          <img 
            src="/logo.png" 
            alt="PojoGalaxy Logo" 
            className="h-[44px] w-auto block"
            style={{ height: '44px', width: 'auto', display: 'block', minHeight: '44px' }} 
          />
        </Link>

        {/* Links */}
        <div className="hidden md:flex items-center space-x-8">
          {navLinks.map((link) => (
            link.external ? (
              <a 
                key={link.name}
                href={link.path}
                target="_blank"
                rel="noopener noreferrer"
                className="text-sm font-medium tracking-wide text-gray-600 transition-colors hover:text-black"
              >
                {link.name}
              </a>
            ) : (
              <Link
                key={link.name}
                to={link.path}
                className={`text-sm font-medium tracking-wide transition-colors hover:text-black ${
                  location.pathname === link.path ? "text-black font-semibold" : "text-gray-600"
                }`}
              >
                {link.name}
              </Link>
            )
          ))}
        </div>
        
        {/* Mobile menu stub */}
        <div className="md:hidden flex items-center">
          <button className="text-gray-600 hover:text-black p-2">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
        </div>
      </div>
    </nav>
  );
}
