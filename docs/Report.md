# Report


## 1. Introduzione


## 2. Modello di dominio


## 3. Requisiti specifici

### 3.1 Requisiti funzionali

### 3.2 Requisiti non funzionali


## 7. Manuale utente
Benvenuto/a nel gioco della battaglia navale! Questo manuale utente fornirà al giocatore le informazioni necessarie per giocare.
### Comandi Disponibili
Il giocatore può utilizzare una serie di comandi utili per impostare e avere informazioni relative al gioco.
#### Comando /help
Si utilizza per visualizzare l'elenco dei comandi disponibili, uno per riga, per esempio:
- gioca
- esci
- ...
#### Comando /esci
Per chiudere il gioco, il giocatore, può utilizzare il comando /esci. L'applicazione richiederà una conferma per procedere. Se il giocatore conferma la chiusura, l'applicazione si chiuderà e il controllo verrà restituito al sistema operativo. Se il giocatore nega la chiusura, l'applicazione sarà pronta a ricevere nuovi tentativi o comandi.
#### Impostazione del livello di gioco
Il giocatore, prima dell'inizio della partita, può decidere tra tre livelli di gioco: facile, medio, difficile. Per poter scegliere il livello si utilizza un comando che varia in base al livello, per esempio se il giocatore vuole impostare il livello facile, dovrà utilizzare il comando /facile. 
##### Comando /facile
Il gioco risponderà con "OK" e imposterà il numero massimo di tentativi falliti a 50.

![comando_facile.png](img%2Fcomando_facile.png)
##### Comando /medio 
Il gioco risponderà con "OK" e imposterà il numero massimo di tentativi falliti a 30.

##### Comando /difficile
Il gioco risponderà con "OK" e imposterà il numero massimo di tentativi falliti a 10.

![comando_difficile.png](img%2Fcomando_difficile.png)
#### Comando /mostralivello
Il giocatore può visualizzare il livello di gioco corrente e il numero massimo di tentativi falliti utilizzando il comando /mostralivello. Il gioco risponderà mostrando queste informazioni.
![comando_mostralivello.png](img%2Fcomando_mostralivello.png)
#### Comando /mostranavi
Per conoscere i tipi di nave presenti nel gioco e il numero di esemplari da affondare per ciascun tipo, il giocatore può utilizzare il comando /mostranavi. 
![comando_mostranavi.png](img%2Fcomando_mostranavi.png)
#### Comando /gioca
Per iniziare una nuova partita, il giocatore può utilizzare il comando /gioca. Se non è in corso alcuna partita, il gioco imposterà casualmente le navi in posizione orizzontale o verticale, mostrerà la griglia vuota e sarà pronta a ricevere il primo tentativo del giocatore o altri comandi.
![comando_gioca.png](img%2Fcomando_gioca.png)
#### Comando /svelagriglia
Utilizzando il comando /svelagriglia il gioco risponde visualizzando, una griglia 10 x 10, con le righe numerate da 1 a 10 e le colonne numerate da A a J, e tutte le navi posizionate.
<br>
### Introduzione di gioco
La **battaglia navale** è un gioco, in questo caso a giocatore singolo, il cui **obiettivo** è quello di **affondare tutte le navi** che sono posizionate su una griglia 10x10 nascosta al giocatore con le righe numerate da 1 a 10 e le colonne numerate da A a J.
### Regole
Prima di iniziare, il giocatore può impostare un **livello di gioco**: **facile**, **medio** o **difficile**; Ognuno di questi livelli ha un numero massimo di tentativi falliti, il livello facile offre un numero di tentativi falliti pari a 50, il livello medio ne offre 30 e il livello difficile ne offre 10.
### Fase iniziale
Le navi saranno posizionate segretamente sulla griglia in maniera casuale dal programma. Ogni nave può essere posizionata orizzontalmente o verticalmente, non in diagonale. Inoltre le navi possono toccarsi, ma non possono occupare le stesse celle. La posizione delle navi non potrà essere modificata dopo l'inizio del gioco.
#### Tipi di nave
Le tipologie di navi si distinguono in base alla dimensione: 
- Cacciatorpediniere 	⊠⊠ 		esemplari: 4 

- Incrociatore 		⊠⊠⊠ 	esemplari: 3  

- Corazzata 		⊠⊠⊠⊠ 	esemplari: 2  

- Portaerei  		⊠⊠⊠⊠⊠ 	esemplari: 1 

### Fase di gioco
Dopo aver selezionato il livello di gioco e posizionato le navi, il giocatore sceglie una cella identificata da una lettera e un numero sulla griglia in cui il colpo sarà lanciato.
Il giocatore ha a disposizione 1 colpo per tentativo.
Il programma controlla se, nella cella scelta sulla griglia dal giocatore, è stata posizionata una nave e in tal caso si visualizzerà il simbolo: x, altrimenti se la cella non contiene una nave si visualizzerà il simbolo: ~ che indica la cella vuota. Quando la nave viene affondata il simbolo varia per ogni tipo di nave.
### Fase finale
In conclusione il gioco termina con una vittoria, quando il giocatore affonda tutte le navi, oppure con una sconfitta, quando il giocatore termina il numero massimo di tentativi falliti.
<br>
### Esecuzione dell'applicazione
Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16.
#### Elenco di terminali supportati

Linux:
- terminal

Windows:
- Powershell
- Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ...)

#### Comando per l'esecuzione del container
Dopo aver eseguito il comando ```docker pull``` copiandolo da GitHub Packages, il comando Docker da usare per eseguire il container contenente l'applicazione è:
```docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-hamming:latest```
<br>
## 9. Analisi retrospettiva

### 9.1 Sprint 0
