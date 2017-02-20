## Анализ Thread Dump с deadlock

Сам thread dump находится в файле «threads_report.txt». Как можно увидеть, есть 2 потока в статусе «BLOCKED»:
- Thread-1@454
- Thread-0@452

Они используют 2 ресурса:
- 0x1c8
- 0x1c7

Первый из них (Thread-1@454) заблокировал второй поток
> blocks Thread-0@452

И в то же время ожидает, пока второй поток осводбодит ресурс 0x1c8
> waiting for Thread-0@452 to release lock on <0x1c8>


Второй поток (Thread-0@452) заблокировал первый
> blocks Thread-1@454

И в то же время ожидает, пока первый поток освободит ресурс 0x1c7
> waiting for Thread-1@454 to release lock on <0x1c7>
