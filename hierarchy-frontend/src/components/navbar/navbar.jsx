import { Link } from "react-router-dom";
import "./navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="container mx-auto flex justify-between items-center">
        <div>
          <Link to="/" className="logo">
            Teste Prático Fullstack
          </Link>
        </div>
        <div className="nav-list">
          <Link to="/" className="nav-link">
            Home
          </Link>
          <Link to="/about" className="nav-link">
            Agradecimento
          </Link>
          <div className="dropdown">
            <button className="dropbtn">Consultar dados ▼</button>
            <div className="dropdown-content">
              <Link to="/JsonResponse" className="nav-link">
                Todos
              </Link>
              <Link to="/NameResponse" className="nav-link">
                Name
              </Link>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
