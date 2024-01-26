import { useState, useEffect } from "react";
import "./DataResponse.css";

const DataResponse = () => {
  const [responseData, setResponseData] = useState(null);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
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

  const handleStartDateChange = (event) => {
    setStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);
  };

  const filterDataByDate = () => {
    if (!responseData || !startDate || !endDate) return;

    const filteredOrders = responseData.filter((user) =>
      user.orders.some(
        (order) => order.date >= startDate && order.date <= endDate
      )
    );

    setFilteredData(filteredOrders);
  };

  return (
    <div className="json-response-container">
      <h2 className="poppins-font">Dados da API:</h2>

      <div className="json-response-filter">
        <div className="date-filter">
          <label htmlFor="startDate">Data Inicial:</label>
          <input
            type="date"
            id="startDate"
            value={startDate}
            onChange={handleStartDateChange}
          />

          <label htmlFor="endDate">Data Final:</label>
          <input
            type="date"
            id="endDate"
            value={endDate}
            onChange={handleEndDateChange}
          />
        </div>

        <div className="button-filter">
          <button className="poppins-font" onClick={filterDataByDate}>
            Filtrar por Data
          </button>
        </div>
      </div>

      <div>
        <h3 className="poppins-font">Resultado Filtrado:</h3>
        <pre>{JSON.stringify(filteredData, null, 2)}</pre>
      </div>
    </div>
  );
};

export default DataResponse;
