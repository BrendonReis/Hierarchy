import React, { useState } from 'react';
import axios from 'axios';
import './styles.css';

const Login = () => {
  const [name, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = async () => {
    try {
      setError('');
      setLoading(true);

      const response = await axios.post('http://localhost:8080/api/employees', {
        name,
        password
      });

    } catch (error) {
      console.error('Erro ao salvar usuário:', error);
      setError('Usuário já cadastrado, tente outro por favor.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-container">
      <h1>Bem-vindo</h1>

      {error && <p className="error-message">{error}</p>}

      <div className="input-container">
        <label htmlFor="name">Nome de Usuário:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={handleUsernameChange}
        />
      </div>

      <div className="input-container">
        <label htmlFor="password">Senha:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={handlePasswordChange}
        />
      </div>

      <div className="button-container">
        <button
          onClick={handleLogin}
          disabled={!name || !password || loading}
          className="upload-button"
        >
          {loading ? "Carregando..." : "Salvar"}
        </button>
      </div>
    </div>
  );
};

export default Login;
