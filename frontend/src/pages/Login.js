import { useState } from "react";
import { useAuth } from "../contexts/AuthContext";
import { toast, Toaster } from "react-hot-toast";

export default function Login() {
  const { login } = useAuth();
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await login(email, senha);
      toast.success("Login realizado com sucesso!");
    } catch (err) {
      toast.error("E-mail ou senha inválidos");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-purple-900 to-indigo-900">
      <Toaster />
      <form
        onSubmit={handleSubmit}
        className="bg-white/10 backdrop-blur p-8 rounded-xl shadow-xl w-full max-w-md"
      >
        <h1 className="text-white text-2xl font-bold mb-6 text-center">
          Acesso ao sistema
        </h1>

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full px-4 py-2 mb-4 rounded bg-white/20 text-white placeholder-white/60 border border-white/30 focus:outline-none"
        />

        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          className="w-full px-4 py-2 mb-6 rounded bg-white/20 text-white placeholder-white/60 border border-white/30 focus:outline-none"
        />

        <button
          type="submit"
          disabled={loading}
          className="w-full bg-purple-600 hover:bg-purple-700 text-white font-semibold py-2 rounded transition"
        >
          {loading ? "Entrando..." : "Entrar"}
        </button>
      </form>
    </div>
  );
}


