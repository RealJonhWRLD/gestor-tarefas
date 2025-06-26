import { createContext, useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const navigate = useNavigate();

  useEffect(() => {
    if (token) {
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }
  }, [token]);

  const login = async (email, senha) => {
    const response = await axios.post("http://localhost:8080/auth/login", {
      email,
      senha,
    });

    const { token: jwt } = response.data;
    localStorage.setItem("token", jwt);
    setToken(jwt);
    setUser({ email }); // ou use dados retornados no login

    axios.defaults.headers.common["Authorization"] = `Bearer ${jwt}`;
    navigate("/tarefas");
  };

  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
    setUser(null);
    delete axios.defaults.headers.common["Authorization"];
    navigate("/login");
  };

  return (
    <AuthContext.Provider value={{ user, token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}

