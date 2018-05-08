Bina Marius Andrei - bina.marius@gmail.com

Pentru rezolvarea problemei de labirint, am citit din fisier matricea de dimensiunile date,
salvand-o ca o matrice de char, pe care apoi am transmis-o mai departe pentru a se forma
matricea specifica fiecarui caracter, a lui Romeo si a Julietei, aceasta fiind de int,
zidurile reprezentate de -1, spatiile goale de 0 si caracterul al carui matrice este va fi
reprezentat de 1. Matricile de int urmeaza a fi completate cu numarul de pasi ce trebuie
facuti de caracterul respectiv pentru a ajunge in fiecare dintre pozitii. Acest lucru se 
realizeaza folosind o coada, coada in care se adauga prima data pozitia initiala a
caracterului, apoi, cat timp coada nu este goala, se scoate un element din ea, se verifica
vecinii acestuia, si punctele invecinate care nu sunt ziduri sunt adaugate si ele in coada
si in matricea de int le sunt modificate valorile cu valoarea punctului anterior incrementat
cu 1. Astfel, primul "rand" de vecini unde poate ajunge caracterul (1), vor avea 2, apoi
vecinii in care se poate ajunge dintr-un punct cu valoarea 2, vor avea 3... and it goes on.
Dupa completarea ambelor matrici, acestea se "suprapun" si se cauta valori egale, diferite
de 0 ( pozitii inaccesible ) sau -1 ( ziduri ). Fiecare valoare gasita este adaugata intr-o
lista. Lista rezultata este parcursa odata pentru a afla valoarea minima a acesteia. Dupa ce
stim numarul minim de pasi pe il fac caracterele, parcurgem pentru ultima oara matricile si
cautam pozitiile unde gasim valori egale intre ele si egale cu numarul minim de pasi. Atunci
printam rezultatul dupa formatul cerut : Nr_minim_pasi X Y
