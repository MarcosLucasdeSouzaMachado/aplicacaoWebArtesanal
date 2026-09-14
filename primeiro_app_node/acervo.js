let acervo = [
  {
    id: 1,
    titulo: "Dom Casmurro",
    autor: "Machado de Assis",
    localizador: "BRA-MA-001"
  },
  {
    id: 2,
    titulo: "Memórias Póstumas de Brás Cubas",
    autor: "Machado de Assis",
    localizador: "BRA-MA-002"
  },
  {
    id: 3,
    titulo: "O Cortiço",
    autor: "Aluísio Azevedo",
    localizador: "BRA-AA-001"
  },
  {
    id: 4,
    titulo: "Vidas Secas",
    autor: "Graciliano Ramos",
    localizador: "BRA-GR-001"
  },
  {
    id: 5,
    titulo: "São Bernardo",
    autor: "Graciliano Ramos",
    localizador: "BRA-GR-002"
  },
  {
    id: 6,
    titulo: "Capitães da Areia",
    autor: "Jorge Amado",
    localizador: "BRA-JA-001"
  },
  {
    id: 7,
    titulo: "Gabriela, Cravo e Canela",
    autor: "Jorge Amado",
    localizador: "BRA-JA-002"
  },
  {
    id: 8,
    titulo: "Grande Sertão: Veredas",
    autor: "João Guimarães Rosa",
    localizador: "BRA-JGR-001"
  },
  {
    id: 9,
    titulo: "A Hora da Estrela",
    autor: "Clarice Lispector",
    localizador: "BRA-CL-001"
  },
  {
    id: 10,
    titulo: "Laços de Família",
    autor: "Clarice Lispector",
    localizador: "BRA-CL-002"
  },
  {
    id: 11,
    titulo: "Macunaíma",
    autor: "Mário de Andrade",
    localizador: "BRA-MDA-001"
  },
  {
    id: 12,
    titulo: "Iracema",
    autor: "José de Alencar",
    localizador: "BRA-JA-001"
  },
  {
    id: 13,
    titulo: "O Guarani",
    autor: "José de Alencar",
    localizador: "BRA-JA-002"
  },
  {
    id: 14,
    titulo: "A Moreninha",
    autor: "Joaquim Manuel de Macedo",
    localizador: "BRA-JMM-001"
  },
  {
    id: 15,
    titulo: "Quarto de Despejo",
    autor: "Carolina Maria de Jesus",
    localizador: "BRA-CMJ-001"
  }
];

export function buscar(termo) {
  if (!termo || termo.trim() === '') {
    return [];
  }
  
  const termoLower = termo.toLowerCase();
  return acervo.filter(livro => 
    livro.titulo.toLowerCase().includes(termoLower)
  );
}