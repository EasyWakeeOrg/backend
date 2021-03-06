<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="icon" type="image/png" th:href="@{/images/icone.png}" /><!-- icone de l'onglet -->
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.min.css}"/>
    <link rel="stylesheet" type="text/css" th:href="@{/css/styles.css}"/>
  </head>
<body>
  <header>
    <nav id="header-nav" class="navbar navbar-default">
      <div class="container">
        <div class="navbar-header">
          <a href="/home" class="pull-left visible-md visible-lg">
            <div id="logo-img"></div>
          </a>

          <div class="navbar-brand">
            <a href="/home"><h1>EasyWakee!<br/>
              It makes your life easy
            </h1></a>
          </div>

          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapsable-nav">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>

        <div id="collapsable-nav" class="collapse navbar-collapse">
           <ul id="nav-list" class="nav navbar-nav navbar-right">
            <li>
              <a href="/home">
                <span class="glyphicon glyphicon-home"></span><br class="hidden-xs"> Home</a>
            </li>
            <li>
              <a href="/form">
                <span class="glyphicon glyphicon-user"></span><br class="hidden-xs"> Change Your Settings</a>
            </li>
            <li>
              <a href="#">
                <span class="glyphicon glyphicon-heart-empty"></span><br class="hidden-xs"> About us</a>
            </li>
          </ul><!-- #nav-list -->
        </div><!-- .collapse .navbar-collapse -->
      </div><!-- .container -->
    </nav><!-- #header-nav -->
  </header>
  <div class="main">
      <div id="settings">
      <script type="text/javascript">
      	console.log("${user}");
      </script>
        <span class="title"><br/>Your settings :</span>
        <ul>
                <li>Time :  <br>
            <div id="TimeDesc", style="display:none;" class="answer_list" >
				This is the time the user need to be ready to leave after waking up.
			</div>
			<input type="button" id="Time button" name="Show Time" value="Hide/Show description >>>" onclick="afficheDetails('TimeDesc')" />
        </li>

        <li>Address<br>
		48 Place de la mairie<br>
		<span id="CP">06000</span> <span id="City">Nice</span><br>
		    <div id="AddressDesc", style="display:none;" class="answer_list" >
				The address of the user is needed for computing the route from his house to his school/workplace.
			</div>
			<input type="button" id="Address button" name="Show Address" value="Hide/Show description >>>" onclick="afficheDetails('AddressDesc')" />
		</li>

		<li>Transport
			<ol>
				<li>Bus</li>
				<li>Walking</li>
				<li>Car</li>
			</ol>
		    <div id="TransportDesc", style="display:none;" class="answer_list" >
				What transport means the user is willing to use in his preference order.
			</div>
			<input type="button" id="Transport button" name="Show Transport" value="Hide/Show description >>>" onclick="afficheDetails('TransportDesc')" />
		</li>
		</ul>
      </div>


      <span>We tried to integrate the meteo api openwheathermap. It works well on
      our computers, but github blocks it since the free version allows htpp only. Please to see it in action
      download a zip copy of this repository.
    </span>
      <div id='openweathermap-widget'></div>
      <footer>
          EasyWakee&amp;Co Copyright &copy; 2017
      </footer>
    </div>

  <!-- jQuery (Bootstrap JS plugins depend on it) -->
  <script type="text/javascript" th:src="@{/js/jquery-2.1.4.min.js}"></script>
  <script type="text/javascript" th:src="@{/js/bootstrap.min.js}"></script>
  <script type="text/javascript" th:src="@{/js/afficheDetails.js}"></script>
  <script th:src="@{/js/meteo.js}" type='text/javascript'></script>
  </body>
</html>
