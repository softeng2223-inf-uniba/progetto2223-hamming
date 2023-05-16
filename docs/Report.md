# Report


## 1. Introduzione


## 2. Modello di dominio


## 3. Requisiti specifici

### 3.1 Requisiti funzionali

- **RF1**: Come giocatore voglio mostrare l'help con l'elenco dei comandi.

    **Criteri di accettazione**

    Al comando **/help** o invocando l'app con flag **--help** o **-h**

    Il risultato è una descrizione concisa, che normalmente appare all'avvio del programma, seguita dalla lista di comandi disponibili, uno per riga, come da esempio:
    - gioca
    - esci
    - ...<br /><br />
- **RF2**: Come giocatore voglio chiudere il gioco.

    **Criteri di accettazione**

    Al comando **/esci**

    L'applicazione chiede conferma:
    - se la conferma è positiva, l'applicazione si chiude restituendo il controllo al sistema operativo
    - se la risposta è negativa, l'applicazione si predispone a ricevere nuovi tentativi o comandi<br /><br />
- **RF3**: Come giocatore voglio impostare il livello di gioco per variare il numero massimo di tentativi sbagliati.

    **Criteri di accettazione**

    - Al comando **/facile**

    L'applicazione risponde con OK e imposta a 50 il numero massimo di tentativi falliti
    - Al comando **/medio**

    L'applicazione risponde con OK e imposta a 30 il numero massimo di tentativi falliti
    - Al comando **/difficile**

    L'applicazione risponde con OK e imposta a 10 il numero massimo di tentativi falliti<br /><br />
- **RF4**: Come giocatore voglio mostrare il livello di gioco e il numero massimo di tentativi falliti.

    **Criteri di accettazione**

    Al comando **/mostralivello**

    L'applicazione risponde visualizzando il livello di gioco e il numero massimo di tentativi falliti<br /><br />
- **RF5**: Come giocatore voglio visualizzare i tipi di nave e il numero.

    **Criteri di accettazione**

    Al comando **/mostranavi**

    L'applicazione risponde visualizzando, per ogni tipo di nave, la dimensione in quadrati e il numero di esemplari da affondare:
    <table><tr>
    <td style="border: none">- Cacciatorpediniere<br />- Incrociatore<br />- Corazzata<br />- Portaerei</td>
    <td style="border: none">⊠⊠<br />⊠⊠⊠<br />⊠⊠⊠⊠<br />⊠⊠⊠⊠⊠</td>
    <td style="border: none">esemplari: 4<br />esemplari: 3<br />esemplari: 2<br />esemplari: 1</td>
    </tr></table><br />
- **RF6**: Come giocatore voglio iniziare una nuova partita.

    **Criteri di accettazione**

    Al comando **/gioca**

    Se nessuna partita è in corso, l'applicazione imposta casualmente le navi, in orizzontale o in verticale, mostra la griglia vuota e si predispone a ricevere il primo tentativo o altri comandi<br /><br />
- **RF7**: Come giocatore voglio svelare la griglia con le navi posizionate.

    **Criteri di accettazione**

    Al comando **/svelagriglia**

    L'applicazione risponde visualizzando una griglia 10 x 10 con le righe numerate da 1 a 10 e le colonne numerate da A a J e tutte le navi posizionate<br /><br />

### 3.2 Requisiti non funzionali

- **RNF1**: Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16.

    **Elenco di terminali supportati**

    Linux:
    - terminal

    Windows:
    - Powershell
    - Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ...)
    
    **Comando per l'esecuzione del container**

    Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, il comando Docker da usare per eseguire il container contenente l'applicazione è:

    ```console
    docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-base2223:latest
    ```
    <br />
- **RNF2**: Il gioco deve offrire un'interfaccia utente intuitiva e facile da usare. (Usabilità)<br /><br />
- **RNF3**: Il gioco deve gestire errori e ogni tipo di situazione in modo da non provocare interruzioni o crash. (Affidabilità)<br /><br />
- **RNF4**: Il gioco deve essere sviluppato in maniera tale da garantire l'aggiunta di nuove funzionalità o miglioramenti senza modificare l'intero codice. (Manutenibilità)<br /><br />
- **RNF5**: Il gioco deve garantire prestazioni ottime in termini di utilizzo di memoria e latenza. (Efficienza)<br /><br />


## 7. Manuale utente


## 9. Analisi retrospettiva

### 9.1 Sprint 0
