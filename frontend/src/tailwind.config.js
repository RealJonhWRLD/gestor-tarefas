/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}"
  ],
  theme: {
    extend: {
      colors: {
        primary: "#3B1E6D",   // tons escuros roxos e azulados
        secondary: "#6C4ACD", // tom médio da imagem
        accent: "#AD82F3",    // destaque mais claro
        background: "#1E1333",
        highlight: "#E1D5FF"
      }
    }
  },
  plugins: [],
}

