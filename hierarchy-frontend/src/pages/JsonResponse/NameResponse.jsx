import React, { useState, useEffect } from "react";
import "./NameResponse.css";

const NameResponse = () => {
  const [responseData, setResponseData] = useState(null);
  const [nameFilter, setNameFilter] = useState("");
  const [filteredData, setFilteredData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/employees");
        const data = await response.json();
        setResponseData(data);
      } catch (error) {
        console.error("Erro ao buscar dados da API:", error);
      }
    };

    fetchData();
  }, []);

  const handleNameFilterChange = (event) => {
    setNameFilter(event.target.value);
  };

  const getColorByForce = (force) => {
    if (force >= 10 && force < 30) return "red";
    if (force >= 30 && force < 50) return "yellow";
    if (force >= 50 && force < 70) return "lightgreen";
    if (force >= 70) return "darkgreen";
  };

  const getTextByForce = (force) => {
    let className = "force-text";
    let label = "";
  
    if (force >= 10 && force < 30) {
      className += " force-text-ruim";
      label = "Ruim";
    } else if (force >= 30 && force < 50) {
      className += " force-text-mediana";
      label = "Mediana";
    } else if (force >= 50 && force < 70) {
      className += " force-text-bom";
      label = "Bom";
    } else if (force >= 70) {
      className += " force-text-forte";
      label = "Forte";
    }
  
    return (
      <div className="force-container">
        <div className="force-box">{force}</div>
        <div className="force-box" style={{ backgroundColor: getColorByForce(force) }}>{label}</div>
      </div>
    );
    
  };
  

  const filterDataByName = () => {
    if (!responseData) return;

    const filteredUsers = responseData.filter(
      (user) => user.name.toLowerCase().includes(nameFilter.toLowerCase())
    );

    setFilteredData(filteredUsers);
  };

  return (
    <div className="json-response-container">
      <h2 className="poppins-font">Dados da API:</h2>

      <div className="json-respons-filter">
        <label htmlFor="nameFilter">Filtrar por Nome:</label>
        <input
          type="text"
          id="nameFilter"
          value={nameFilter}
          onChange={handleNameFilterChange}
        />
        <button className="poppins-font" onClick={filterDataByName}>
          Filtrar
        </button>
      </div>

      <div>
        <h3 className="poppins-font">Resultado Filtrado:</h3>
        {filteredData &&
          filteredData.map((user) => (
            <div key={user.id}>
              <p>{user.name}</p>
              <p style={{ color: getColorByForce(parseInt(user.force, 10)) }}>
                {getTextByForce(parseInt(user.force, 10))}
              </p>
            </div>
          ))}
      </div>
    </div>
  );
};

export default NameResponse;
