# Report


## 1. Introduzione


## 2. Modello di dominio
![Modello concettuale](../drawings/ModelloConcettuale.png)

### Concetti

- Cella

    Rappresenta una singola cella all'interno della griglia di gioco.
    - Attributi
        - colpita: boolean
            
            Indica se la cella è stata colpita da un attacco del giocatore.

- CellaPiena

    Specializzazione di Cella. Rappresenta una cella che è stata occupata da una nave.

- Griglia

    Griglia di gioco. È composta da una tabella 10x10 di celle.


- Nave

    Nave che occupa due o più celle piene sulla griglia.
    
    - Attributi

        - tipologia: string

            Indica il tipo di nave. Può essere una delle seguenti:
            - portaerei
            - corazzata
            - incrociatore
            - cacciatorpediniere

        - celleRimanenti: int
            
            Indica il numero di celle non ancora colpite della nave.


- Partita

    Rappresenta una singola partita di gioco. Contiene le navi posizionate e la griglia di gioco.

    - Attributi

        - livello: string

            Livello di difficoltà della partita. Può essere uno dei seguenti:
            - facile
            - medio
            - difficile

        - tentativiRimasti: int
                
            Numero di tentativi rimasti prima della sconfitta.

- Comando

    Rappresenta un comando con cui l'utente gestisce la partita.

    - Attributi

        - nome: string

            Nome del comando. Corrisponde alla stringa che l'utente deve inserire per eseguirlo.
        
        - categoria: string

            Categoria a cui appartiene il comando.


## 3. Requisiti specifici

### 3.1 Requisiti funzionali

### 3.2 Requisiti non funzionali


## 7. Manuale utente


## 9. Analisi retrospettiva

### 9.1 Sprint 0
