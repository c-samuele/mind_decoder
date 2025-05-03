# Master Mind

## Motivazione e obiettivi

Il gruppo si pone come obiettivo la realizzazione di un gioco di decodifica in stile Master Mind.

L'obiettivo è indovinare una sequenza segreta di colori entro un numero finito di tentativi o un limite di tempo.

Al termine di ogni tentativo, vengono forniti indizi tramite:
- **segnale nero**: se un colore è corretto e nella posizione giusta.
- **segnale bianco**: un colore è corretto ma nella posizione sbagliata.

Il gioco termina con un fallimento se il giocatore esaurisce i tentativi senza aver indovinato la sequenza (o se scade il tempo). Termina con un successo non appena il giocatore indovina la sequenza segreta.

Il gioco può essere svolto in:
- **Single player**: La sequenza segreta verrà generata in modo casuale, il giocatore dovrà trovarla entro un numero finito di tentativi, il gioco si estende su più livelli.
- **Multi player**: Ogni giocatore crea la propria sequenza segreta con lo stesso numero di colori. Il giocatore deve riuscire a individuarla con un tempo minore dell'avversario (se modalità a tempo) o con un punteggio maggiore (se in modalità a tentativi), in caso di pareggio si considerano i tentativi utilizzati e il tempo impiegato.

## Funzionalità minimali ritenute obbligatorie

- Livelli
- Punteggio e Tentativi
- Legenda e regole di gioco
- Indizi partita
- Statistiche di gioco

## Funzionalità opzionali

- Modalità 2 giocatori
- Modalità a tempo
- Modalità difficile (più colonne e meno tentativi)

## "Challenge" principali

- Creazione di un controllore che al termine della "giocata" permetta di dare consigli all'utente oltre che verificare la corretta applicazione delle meccaniche implementate.
- Realizzare una GUI per semplificare e rendere intuitiva la modalità di gioco.

## Suddivisione del lavoro

- Samuele si occuperà del design della GUI e del Model.
- Cristiano si occuperà del Control e dell'implementazione della GUI.
