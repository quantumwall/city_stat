<p><em>Тестовое задание на позицию Java junior developer</em></p>

<p>
Программа парсит csv и xml файлы с информацией вида:
<pre>"city";"street";"house";"floor"
"Барнаул";"Дальняя улица";56;2
...
</pre>
или
<pre>&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;root&gt;
	&lt;item city="Барнаул" street="Дальняя улица" house="56" floor="2" /&gt;
	...
</pre>
и выводит сводную статистику и количество дублирующихся записей на экран.
</p>
<p>
Запуск приложения:
<ol>
	<li>В корне проекта, в командной строке выполнить <pre>./gradlew jar (./gradlew.bat jar для windows)</pre></li>
	<li>Для запуска приложения <pre>java -jar build/libs/city_stat.jar</pre></li>
</ol>
</p>
