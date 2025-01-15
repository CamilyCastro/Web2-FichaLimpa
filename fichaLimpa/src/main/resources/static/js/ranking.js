document.addEventListener("DOMContentLoaded", () => {
        const rankingList = document.getElementById("ranking-list");

        
        const fetchRanking = async () => {
            try {
                const response = await fetch("/api/politico/ranking");
				console.log(response);
				
                if (!response.ok) {
                    throw new Error("Erro ao buscar o ranking");
                }
                const ranking = await response.json();
                updateRanking(ranking);
            } catch (error) {
                console.error("Erro ao carregar o ranking:", error);
                rankingList.innerHTML = "<li>Erro ao carregar o ranking.</li>";
            }
        };

        
        const updateRanking = (ranking) => {
            rankingList.innerHTML = ""; // Limpa o ranking anterior
            ranking.forEach((politico, index) => {
                const listItem = document.createElement("li");
                listItem.innerHTML = `
                    <span>${index + 1}. <strong>${politico.nome}</strong> - Nota: ${politico.nota.toFixed(2)}</span>
                `;
                rankingList.appendChild(listItem);
            });
        };

        
        fetchRanking();
    });