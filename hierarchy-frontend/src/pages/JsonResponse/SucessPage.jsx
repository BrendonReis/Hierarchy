import React from 'react';

const SuccessPage = ({ location }) => {
  const responseData = location.state.responseData;

  return (
    <div>
      <h1>Sucesso!</h1>
      <p>Dados da resposta:</p>
      <pre>{JSON.stringify(responseData, null, 2)}</pre>
    </div>
  );
};

export default SuccessPage;
