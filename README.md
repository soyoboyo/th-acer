# DB Exporter

Tematem zadania jest narzędzie do eksportu zawartości bazy danych do postaci tekstowej.

Aplikacja powinna być uruchamiana w sposób wsadowy (linia komend) i eksportować do formatu CSV dane ze wskazanej bazy danych. Założeniem aplikacji są prostota użytkowania, wysoka reużywalność i konfigurowalność.

Narzędzie przy starcie wymaga podania:

```
{
    adresu do połączenia z bazą danych, 
    ścieżki do sterownika jdbc, 
    nazwy tabeli, 
    nazw kolumn do eksportu, 
    opcjonalnej paginacji (rozmiar i numer strony)
    lub wykorzystania jednej z wcześnie używanych konfiguracji. 
} 
```

Aplikacja powinna dawać możliwość pracy z różnymi bazami danych (np. Oracle, Postgresql), ale jednocześnie nie być ściśle związana z żadną z nich. Nie powinna zawierać wbudowanego sterownika jdbc. Dane zwracane są na standardowym wyjściu.


---

```
CREATE TABLE EMPLOYEE
(
    id   numeric not null,
    name varchar not null,
    constraint employee_id primary key (id)
);
CREATE TABLE INVOICE
(
    id          numeric not null,
    employee_id numeric not null,
    price       numeric not null,
    year        numeric not null,
    constraint invoice_id primary key (id),
    foreign key (employee_id) REFERENCES employee (id)
);

Insert into EMPLOYEE values (1, 'Adam');
Insert into EMPLOYEE values (2, 'Bartek');
Insert into EMPLOYEE values (3, 'Cezary');

INSERT into INVOICE values (1, 1, 6, 2020);
INSERT into INVOICE values (2, 1, 8, 2017);
INSERT into INVOICE values (3, 1, 6, 2020);
INSERT into INVOICE values (4, 2, 7, 2018);
INSERT into INVOICE values (5, 2, 8, 2020);
INSERT into INVOICE values (6, 3, 14, 2020);
```

# JDBC download links

https://jdbc.postgresql.org/download.html



