BINA MARIUS ANDREI - bina.marius@gmail.com

Brackets :
ClosedBracket si OpenBracket sunt implementarile intefetei IBracket,
contin un String privat ce reprezinta simbolul lor, tipul de paranteza
si constructorii din interfata parinte IToken.

BracketsFactory :
Clasa de tip Singleton datorita intantierii singulare, obtinem aceasta
instanta prin apelul metodei statice getInstance(). Factory Pattern-ul
se regaseste in metoda createBracket care creeaza un tip de paranteza in
functie de tokenul gasit.
Metodele isOpenedBracket si isClosedBracket verifica daca simbolul parantezei
este fie de tip paranteza stanga ( opened ), fie de tip paranteza dreapta
( closed ), de orice fel. Metoda isBracket le foloseste pe aceste doua pentru
a verifica daca tokenul primit este paranteza sau nu, deoarece daca este paranteza
este obligatoriu inchisa sau deschisa.
isBracketPair verifica daca cele doua paranteze primite au simboluri opuse,
paranteza stanga si paranteza dreapta.

Operators :
Operatorii se impart si ei in doua interfete IBinaryOperator si IUnaryOperator
care sunt implementate de BinaryOperator, respectiv UnaryOperator, care apoi sunt
extinse de clase pentru fiecare tip de operator ( adunare, scadere, radical, etc. )
unde este suprascrisa metoda calculate pentru a face tipul de operatie corespunzatoare
pentru operatorul respectiv.

OperatorsFactory :
Clasa de tip Singleton datorita instantei unice care se acceseaza prin apelul
metodei statice getInstance(). Metoda care ne ajuta sa creem un operator, 
createOperator, verifica tipul de token primit si in functie de operatia careia
ii corespunde, returneaza un anumit tip de operator. Pentru verificarea unui token
daca acesta este operator se compara simbolul acestuia cu cele ale operatorilor
pe care ii cunoastem. Asemanator si isUnaryOperator si isBinaryOperator care verifica
daca simbolul corespunde unui tip de operator unar, respectiv binar.

Server :
Metoda subscribe adauga un operator ( String ) in lista de operatori permisi,
adica lista de operatori pe care ii putem folosi la testul curent.

Metoda canPublish verifica daca un vector de tokene ce reprezinta ecuatia data,
impartita dupa spatii, contine operatii nepermise, in cazul in care gaseste o 
operatie care nu este permisa returneaza false, iar daca trece prin toate tokenele
fara sa gaseasca unul nepermis inseamna ca se poate efectua ecuatia.

Metoda publish primeste o comanda intreaga, o imparte dupa spatii, verifica daca
toate operatiile din ea sunt operatii permise, daca nu sunt operatii permise
adauga in lista de rezultate "IMPOSSIBRU" si nu trece mai departe. In cazul in care
toate operatiile sunt permise, se realieaza forma postfixata, implementand algoritmul
folosind o stiva, apoi se evalueaza forma postfixata scotand din stiva cate doua
numere in cazul operatorilor binari si unul in cazul operatorilor unari, se evalueaza
si rezultatul se pune pe stiva, la final rezultatul final va ramane pe stiva. Este
convertit din Double in cifre romane si este adaugat in lista de rezultate.


