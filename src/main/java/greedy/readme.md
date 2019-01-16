### Условия

* segments
По данным n отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков содержит хотя бы одну из точек.
В первой строке дано число ```1≤n≤100``` отрезков. Каждая из последующих n строк содержит по два числа ```0≤l≤r≤109```,
 задающих начало и конец отрезка. Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.
 
* backpack
Первая строка содержит количество предметов ```1≤n≤103``` и вместимость рюкзака ```0≤W≤2⋅106.``` Каждая из следующих n
 строк задаёт стоимость ```0≤ci≤2⋅106``` и объём ```0<wi≤2⋅106``` предмета (n, W, ci, wi — целые числа).
 Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть,
 стоимость и объём при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 
* huffman
    * HuffmanCode
    По данной непустой строке 𝑠 длины не более `10^4`, состоящей из строчных букв латинского алфавита, постройте оптимальный
     беспрефиксный код. В первой строке выведите количество различных букв `k`, встречающихся в строке, и размер получившейся
      закодированной строки. В следующих `k` строках запишите коды букв в формате `"letter: code"`. В последней строке выведитезакодированную строку.
    * HuffmanDecode
    Восстановите строку по её коду и беспрефиксному коду символов.  
    В первой строке входного файла заданы два целых числа `k` и `l` через пробел — количество различных букв, встречающихся в строке,
     и размер получившейся закодированной строки, соответственно. В следующих `k` строках записаны коды букв в формате `"letter: code"`.
      Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке. В качестве букв могут встречаться лишь
       строчные буквы латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке записана
        закодированная строка. Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет минимальный возможный размер.
    В первой строке выходного файла выведите строку `s`. Она должна состоять из строчных букв латинского алфавита. Гарантируется,
     что длина правильного ответа не превосходит 104 символов.