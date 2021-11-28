# JAVA TRAINING 2021

### Lorena Andra Vacarean

## Hospital Simulator

### Main Flow
Gandirea flow-ului e facuta pentru a simula mai multe runde (zile) dintr-un spital normal (in care vin noi pacienti,

se angajeaza noi doctori si asistente, se trateaza pacienti si se trimit acasa). Pentru testarea curenta am implementat o

singura zi cu 10 pacienti, 8 doctori si 5 asistente.

Etapele unei runde sunt:

- venirea doctorilor in spital;
- primirea pacientilor in sala de urgente;
- venirea asistentelor in spital;
- sortarea pacientilor in ordine descrescatoare dupa varsta (se presupune ca cei mai in varsta au prioritate in

  sala de urgente;
- preluarea pacientilor de catre asistentele disponibile;
- update in baza de date a spitalului a statusului curent al fiecarui pacient;
- distribuirea (aproximativ egala) pacientilor catre doctorii de garda in sala de urgente;
- eliberarea asistentelor;
- consultarea si diagnosticarea pacientilor de catre doctori;
- update la statusul pacientilor;
- efectuarea de operatii in cazurile grave de catre chirurgi;
- update la statusul doctorilor;
- afisarea rezultatelor;


## Design Patterns

Ca si design pattern am ales Singleton pentru implementarea bazei de date a simularii si Strategy pentru implementarea

tipurilor de comparatori pentru sortari (descrescator dupa varsta si crescator dupa id).

## Entities

Pentru stocarea datelor si interactiunea dintre entitati, am creat 3 mari clase: Doctor, Patient si Nurse; folosesc

enums pentru o mai buna organizarea a valorilor posibile pentru boli, starile pacientului si specialitatile medicilor.

