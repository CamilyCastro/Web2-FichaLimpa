document.addEventListener("DOMContentLoaded", () => {
    const rankingTableBody = document.getElementById("ranking-table-body");

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
            rankingTableBody.innerHTML = "<tr><td colspan='3'>Erro ao carregar o ranking.</td></tr>";
        }
    };

    const updateRanking = (ranking) => {
        rankingTableBody.innerHTML = "";
        ranking.forEach((politico, index) => {
            const row = document.createElement("tr");

            const indexCell = document.createElement("td");
			if (index === 0) {
                indexCell.innerHTML = `${index + 1}º <img src="/images/primeiro.png" width="5%">`;
            } else if (index === 1) {
                indexCell.innerHTML = `${index + 1}º <img src="/images/segundo.png" width="5%">`;
            } else if (index === 2) {
                indexCell.innerHTML = `${index + 1}º <img src="/images/terceiro.png" width="5%">`;
            } else {
                indexCell.textContent = `${index + 1}º`;
            }
            row.appendChild(indexCell);
			
			

            const nameCell = document.createElement("td");
            nameCell.textContent = politico.nome;
            row.appendChild(nameCell);

            const gradeCell = document.createElement("td");
            gradeCell.textContent = politico.nota.toFixed(2);
            row.appendChild(gradeCell);

            rankingTableBody.appendChild(row);
        });
    };

    fetchRanking();
});
