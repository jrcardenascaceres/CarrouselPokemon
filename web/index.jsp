<%-- 
    Document   : PokeAvatar
    Created on : 05/05/2021, 05:22:31 PM
    Author     : jcardenas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Avatar|Pokemon</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="card text-white mt-2 mb-3">
                <div class="card-header bg-primary">Desafio: Integrar la API "PokeApi" a un Carousel</div>
                <div class="card-body">
                    <div id="carouselExampleCaptions" class="carousel carousel-dark slide" data-bs-ride="carousel">
                        <div class="carousel-indicators">
                        </div>
                        <div class="carousel-inner">
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
            <input type="hidden" value="https://pokeapi.co/api/v2/pokemon-species" id="urlApi">
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header bg-primary">
                            <h5 class="modal-title" id="exampleModalLabel">Pokemon: <span id="namePoke"></span></h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body"></div>
                    </div>
                </div>
            </div>
            <div id="foo"></div>
        </div>
        
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- spin.js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/spin.js/2.3.2/spin.min.js"></script>
        <script>
            var spinner = new Spinner({
                lines: 13, // The number of lines to draw
                length: 38, // The length of each line
                width: 17, // The line thickness
                radius: 45, // The radius of the inner circle
                scale: 1, // Scales overall size of the spinner
                corners: 1, // Corner roundness (0..1)
                speed: 1, // Rounds per second
                rotate: 0, // The rotation offset
                animation: 'spinner-line-fade-quick', // The CSS animation name for the lines
                direction: 1, // 1: clockwise, -1: counterclockwise
                color: '#3c6efd', // CSS color or array of colors
                fadeColor: 'transparent', // CSS color or array of colors
                top: '50%', // Top position relative to parent
                left: '50%', // Left position relative to parent
                shadow: '0 0 1px transparent', // Box-shadow for the lines
                zIndex: 2000000000, // The z-index (defaults to 2e9)
                className: 'spinner', // The CSS class to assign to the spinner
                position: 'absolute' // Element positioning
            });
            var target = document.getElementById("foo");
            
            $(function () {
                getPokemon();
                $('#carouselExampleCaptions').on('slide.bs.carousel', function (e) {
                    console.log("Hola "+e);
                });
            });
            
            function getInfoPoke(infoUrl){
                $.ajax({
                    url: "GetPokemon",
                    type: "GET",
                    dataType: "json",
                    data: {
                        infoUrl: infoUrl
                    },
                    timeout: 600000,
                    beforeSend: function () {
                        spinner.spin(target);
                    },
                    success: function (data) {
                        $('#namePoke').html(data.name);
                        $('.modal-body').html(data.bodyModal);
                        $('#exampleModal').modal('show');
                    },
                    error: function (xhr) {
                        console.log("Error:" + xhr);
                    },
                    complete: function () {
                        spinner.stop(target);
                    }
                });
            }

            function getPokemon() {
                $.ajax({
                    url: "GetPokemon",
                    type: "POST",
                    dataType: "json",
                    data: {
                        urlApi: $("#urlApi").val()
                    },
                    timeout: 600000,
                    beforeSend: function () {
                        spinner.spin(target);
                    },
                    success: function (data) {
                        console.log("Success");
                        $('.carousel-indicators').html(data.indicators);
                        $('.carousel-inner').html(data.items);
                        $('#urlApi').val(data.urlApi);
                    },
                    error: function (xhr) {
                        console.log("Error:" + xhr);
                    },
                    complete: function () {
                        spinner.stop(target);
                    }
                });
            }
        </script>
    </body>
</html>