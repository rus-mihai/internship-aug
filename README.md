# internship-aug
internship

//SPECIFICATIE PROIECT
// PAGINI SI USE-CASES

INDEX

-la accesare se deschide prima pagina care este vizibila pentru orice utilizator
-pagina principala contine lista cu liniile de transport
-pagina principala contine un meniu Login care deschide un formulat de autentificare

LOGIN
-se introduc datele de autentificare. Daca datele sunt corespunzatoare se deschide o pagina in functie de tipul de utilizator.
-tipul de utilzator poate fi administrator sau driver. Acesta este determinat automat la autentificare.

ADMINISTRATOR
-daca tipul de utilizator care s-a autentificat este administrator atunci se deschide interfata de administrator
-se pot vizualiza rutele prin apasarea link-ului corespunzator.
-se pot vizualiza soferii prin apasarea link-ului corespunzator.

ADMINISTRARE RUTE
-listeaza rutele existente in sistem
-se pot adauga rute noi utilizand link-ul corespunzator.
-se pot vizualiza/actualiza/modifica detaliile traseelor existente.

DETALII RUTA
-afiseaza traseul pe o harta google maps
-afiseaza numarul rutei
-afiseaza capetele liniei
-detaliile pot fi actualizate
-dupa actualizare se salveaza modificarile.

ADMINISTRARE SOFERI
-listeaza soferii existenti in sistem
-se pot adauga soferi noi
-se pot vizualiza/modifica/actualiza detaliile soferilor si rutele pe care ei pot sa lucreze utilizand link-ul corespunzator

DETALII SOFER
-afiseaza detaliile soferului
-afiseaza rutele pe care lucreaza
-permite adaugarea unei rute noi pe care poate sa lucreze
-permite stergerea de rute pe care poate sa lucreze.

INTERFATA SOFER
-selecteaza ruta pe care lucreaza
-foloseste butoanele de START, INTERMEDIAR si SFARSIT pentru a inregistra timpii in baza de date
-vizualizeaza harta pentru traseul curent

RAPORT RUTA PUBLIC
-afiseaza pe ore, grafic cu o culoare, cum se circula pe ruta respectiva, atat la tur cat si la retur.

USE-CASES:

 VIZUALIZARE RUTA DE UTILIZATOR NEAUTENTIFICAT
  -acceseaza prima pagina > selecteaza din meniu ruta dorita > se deschide pagina "RAPORT RUTA PUBLIC" pentru linia respectiva care afiseaza informatiile

 ADAUGARE RUTA
  -acceseaza prima pagina > acceseaza link-ul pentru LOGIN > introduce username si parola pentru cont de tip administrator > Selecteaza sectiunea rute > completeaza formularul pentru ruta noua
 
 MODIFICARE RUTA (presupunem ca suntem deja autentificati ca administrator)
  -Selecteaza sectiunea rute > alege ruta din lista > modifica ruta > salveaza modificarile

 ADAUGARE SOFER (presupunem ca suntem deja autentificati ca administrator)
  -Selecteaza sectiunea soferi > completeaza formularul pentru adaugare sofer (nu i se asigneaza nici o ruta la creeare)
 
 ASIGNARE RUTA PENTRU SOFER (presupunem ca suntem deja autentificati ca administrator)
  -Selecteaza sectiunea soferi > selecteaza soferul > i se asigneaza ruta

 
 
  




