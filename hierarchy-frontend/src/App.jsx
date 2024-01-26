// App.js
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
} from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import About from "./pages/About";
import JsonResponse from "./pages/JsonResponse/JsonResponse";
import Navbar from "./components/navbar/navbar"; 
import NameResponse from "./pages/JsonResponse/NameResponse";
import DataResponse from "./pages/JsonResponse/DataResponse";

function App() {
  return (
    <Router>
      <div>
        <Navbar />
        <div>
          <Routes>
            <Route
              path="/"
              element={
                <div>
                  <Outlet />
                </div>
              }
            >
              <Route index element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/jsonResponse" element={<JsonResponse />} />
              <Route path="/nameResponse" element={<NameResponse />} />
              <Route path="/dataResponse" element={<DataResponse />} />
            </Route>
          </Routes>
          
        </div>
      </div>
    </Router>
  );
}

export default App;
