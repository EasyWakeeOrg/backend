document.addEventListener("DOMContentLoaded", function (event) {
// Creation du widget meteo
window.myWidgetParam = {
    id: 15,
    cityid: 0,
    appid: '91192d3031aace58be1b29211cb36350',
    containerid: 'openweathermap-widget',
};
  // Requete HTTP
  function loadXMLDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
          meteo(this);
        }
    };
    var city = document.getElementById("City").textContent;
    xhttp.open("GET", "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=91192d3031aace58be1b29211cb36350", true);
    xhttp.send();
  }

    // Récupération de l'id de la ville dans le span "City"
    function meteo(city){
      var i = 0;
      var response = "";
      var cityJson = JSON.parse(city.responseText); // Parse la reponse en Json
      window.myWidgetParam.cityid = cityJson.id;
      if(window.myWidgetParam.cityid !=0){
      // Requête pour avoir le bon temps associé à la ville dans le span "City"
          var script = document.createElement('script');
          script.type = 'text/javascript';
          script.async = false;
          script.src = 'https://openweathermap.org/themes/openweathermap/assets/vendor/owm/js/weather-widget-generator.js';
          var s = document.getElementsByTagName('script')[0];
          s.parentNode.insertBefore(script, s);
        }
    }
  //Appel de la fonction
  loadXMLDoc();
});
