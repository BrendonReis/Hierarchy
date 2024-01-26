import { useState, useEffect } from "react";
import "./JsonResponse.css";

const JsonResponse = () => {
  const [responseData, setResponseData] = useState(null);

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

  return (
    <div className="json-response-container">
      <h2>Dados da API:</h2>
      <div>
        <pre>{JSON.stringify(responseData, null, 2)}</pre>
      </div>
    </div>
  );
};

export default JsonResponse;
