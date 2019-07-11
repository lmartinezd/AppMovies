# AppMovies
Padrón de arquitectura MVVM usando Room, RxJava 2, Retrofit.

# Preguntas
<ol>
<li>En qué consiste el principio de responsabilidad única? Cuál es su propósito?</br>
El SRP del SOLID consiste en agrupar reglas del negocio similares en una sola clase y debe estar encapsulada. Su propósito es de tener un código ordenado de manera que sea de fácil lectura y manutención.</li>

<li>Qué características tiene, según su opinión, un "buen" código o código limpio?</br>
Usar clean code beneficia la fácil lectura y escritura de código para una buena comprensión y manutención.
</li>
</ol>

# Librerías
<ul> 
<li><a href="https://square.github.io/retrofit/">Retrofit 2</a></li>
<li><a href="https://github.com/bumptech/glide">Glide</a></li>
<li><a href="https://github.com/ReactiveX/RxJava">RxJava</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/room.html">Room</a></li>
<li><a href="http://facebook.github.io/stetho/">Stetho</a></li>
</ul>

# Capas de aplicación:
<ul> 
<li>Data: lógica del negócio</br>
local, clases para crear llamada al Room, Dao y entidades</br>
remote, clase para obtener llamada response</br>
repositoy, llamadas al API utilizando Retrofit</r>
</li>
<li>UI: interface de usuário de la aplicación, cada clase está acompañado de un ViewModel que será una intermediária entre las UI y las llamadas al Retrofit o Room.</li>
<li>Utils: declarar variables constantes, métodos para reutilizar e interface para RX.</li>
</ul>
