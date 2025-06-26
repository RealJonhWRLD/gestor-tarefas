import { useEffect, useState } from "react";
import api from "../services/api";
import { useAuth } from "../contexts/AuthContext";
import { toast } from "react-toastify";
import { motion } from "framer-motion";

export default function Tarefas() {
  const { logout } = useAuth();
  const [tarefas, setTarefas] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [totalPaginas, setTotalPaginas] = useState(1);

  const carregarTarefas = async (paginaAtual = 0) => {
    try {
      const response = await api.get(`/tarefas?page=${paginaAtual}&size=5`);
      setTarefas(response.data.content || response.data);
      setTotalPaginas(response.data.totalPages || 1);
    } catch (err) {
      toast.error("Erro ao carregar tarefas");
      if (err.response?.status === 403) logout();
    }
  };

  useEffect(() => {
    carregarTarefas(pagina);
  }, [pagina]);

  return (
    <div className="min-h-screen bg-gradient-to-br from-background to-surface text-white p-4">
      <div className="max-w-4xl mx-auto relative">
        <button
          onClick={logout}
          className="absolute top-0 right-0 bg-red-600 hover:bg-red-700 text-white px-4 py-2 rounded"
        >
          Sair
        </button>

        <motion.h1
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6 }}
          className="text-3xl font-bold mb-4"
        >
          Minhas Tarefas
        </motion.h1>

        <ul className="space-y-3">
          {tarefas.map((tarefa) => (
            <motion.li
              key={tarefa.id}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.3 }}
              className="bg-primary/20 border border-primary p-4 rounded shadow-sm"
            >
              <div className="flex justify-between items-center">
                <div>
                  <h2 className="text-xl font-semibold">{tarefa.titulo}</h2>
                  <p className="text-sm text-gray-300">{tarefa.descricao}</p>
                </div>
                <span className={`text-xs px-2 py-1 rounded-full ${tarefa.concluida ? 'bg-green-500' : 'bg-yellow-500'}`}>
                  {tarefa.concluida ? 'Concluída' : 'Pendente'}
                </span>
              </div>
            </motion.li>
          ))}
        </ul>

        <div className="flex justify-between items-center mt-6">
          <button
            onClick={() => setPagina((p) => Math.max(p - 1, 0))}
            disabled={pagina === 0}
            className="px-4 py-2 bg-secondary text-white rounded disabled:opacity-50"
          >Anterior</button>

          <span>Página {pagina + 1} de {totalPaginas}</span>

          <button
            onClick={() => setPagina((p) => Math.min(p + 1, totalPaginas - 1))}
            disabled={pagina + 1 >= totalPaginas}
            className="px-4 py-2 bg-secondary text-white rounded disabled:opacity-50"
          >Próxima</button>
        </div>
      </div>
    </div>
  );
}
