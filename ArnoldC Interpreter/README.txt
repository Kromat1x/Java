Marius Bina - bina.marius@gmail.com

  In linii mari, implementarea consta in citirea din fisier pe blocuri de instructiuni,
practic, nu vom avea niciodata HEY CHRISTMAS TREE care sa nu fie urmat de YOU SET US UP, adica
nu vom avea niciodat o variabila declarata dar neinitializata, datorita acestor particularitati
ale instructiunilor am optat pentru citirea lor pe blocuri. Astfel, sunt cautate inceputurile
acestor blocuri de instructiuni, blocurile fiind : declararea unei variabile, printarea, if,
while, assignment. Instructiunile de tip artimetic sau logic nu vor fi cautate ca inceput de bloc
de instructiuni deoarece ele se vor gasi numai in noduri de body pentru if, while sau in noduri
pentru calcularea unei valori la assignment. Blocurile sunt prelucrate recursiv, deoarece putem gasi
si alte blocuri in interiorul acestora. Practic, recursivitatea se opreste cand ajungem la noduri de
tip frunze precum LvalNode, ConstantNode, StringNode etc. Pentru a facilita implementarea acestor
blocuri, ele sunt rasturnate, pentru ca daca citim o instructiune de jos in sus, obtinem practic
formarea arborelui de jos in sus, exact ceea ce avem nevoie. Singurele cazuri in care nu rasturnam
blocul de instructiune este acela cand prima instructiune din bloc este un if, un while sau un assignment.
Deoarece if si while sunt tratate separat si se foloseste metoda de build body care le creeaza bodyurile,
iar assignmentul isi rastoarna lista de instructiuni atunci cand este apelat, rasturnarea listei de doua ori
ar fi insemnat ca assignmentul nu ar fi functionat cum ne am fi asteptat.
  Pentru printare este folosit un visitor, care parcurge arborele si in functie de nodul vizitat, printeaza
un anumit output, pentru cele mai simple noduri le este printat numele acestora si argumentul principal pe care
il au, de exemplu LvalNode <varName>, pentru nodurile care au copii, se apeleaza visitorul si pe acestia, practic
arborele este vizitat recursiv. Prin printare intelegem ca se updateaza outputul, care la finalul visitorului,
este practic reprezentarea arborelui. Pentru indentare folosim un string simplu numit tabs, la care adaugam mereu
taburi, dar si o lista pentru taburile salvate, deoarece daca apelam visit pe un copil al unui nod cu o anumita
valoare pentru tabs, in visitul acestuia stringul tabs va fi modificat, astfel ca inainte de a visita un copil,
adaugam tabs in lista savedTabs si retinem indexul unde a fost adaugat pentru a-l restaura inainte de a visita orice
alt copil. In felul acesta nodurile de pe acelasi nivel sunt indentate cu acelasi numar de taburi.
  Pentru interpretare este folosit un alt visitor, care parcurge arborele, dar spre deosebire de printare, aici nu toate
nodurile au un output cand sunt vizitate, singurele noduri care prelucreaza outputul sunt cele de print care pot printa
variable, stringuri sau constante. Pentru a facilita interpretarea arborelui, am ales folosirea unei metode auxiliare
numita doOperation, folosita pentru toate operatiile artimetice si logice, ea primeste un nod, de tip operatie aritmetica
sau logica, si returneaza un int ca rezultat al operatiei date. Aceasta metoda se apeleaza recursiv pana cand ajunge
la un RvalNode sau ConstantNode, astfel metoda poate sa primeasca un nod n, de tip SumNode, pentru exemplu, care sa
fie urmat de o multime de alte operatii artimetice sau logice, functia va returna suma dintre apelurile recursive ale
functiei do operation pe copilul stang si copilul drept al nodului nostru, in exemplu de tip sum. Nodurile de body
au o implementare aproximativ la fel cu main node, deoarece putem avea oricate noduri in ele, de tipuri diverse.
Din nou se foloseste un apel recursiv al visitorului.
