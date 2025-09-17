# Master Mind

## Motivazione e obiettivi

I'obiettivo è la realizzazione di un gioco di decodifica in stile Master Mind.

Lo scopo è indovinare una sequenza segreta di colori entro un numero finito di tentativi.

Al termine di ogni tentativo, vengono forniti indizi:
- **Il numero di colori corretti**
- **Il numero di colori in posizione corretta**

Il gioco termina con un fallimento se il giocatore esaurisce i tentativi senza aver indovinato la sequenza.
Il gioco termina con un successo se il giocatore indovina la sequenza segreta.

Il gioco può essere svolto in:
- **Single player**: La sequenza segreta verrà generata in modo pseudocasuale, il giocatore dovrà trovarla entro un numero finito di tentativi, il gioco si estende su più livelli.
- **Ai Challenge**: La sfida viene fatta contro un'algoritmo basato su distribuzioni di probabilità che tiene traccia delle posizioni più probabili. 

## Funzionalità minimali ritenute obbligatorie

- Single player.
- Ai Challenge.
- Livelli.
- Statistiche con livello corrente, punteggio, tentativi medi e tempo impiegato.
- Suggerimenti per tentativo effettuato.

## Funzionalità opzionali

- Modalità multy player.
- Drag and drop sui colori.

## "Challenge" principali

- Algoritmo di distribuzione probabilitstica per la cpu. 
- Creazione di un controllore per i tentativi.
- Gestione dei comportamenti imprevisti dell'utente.