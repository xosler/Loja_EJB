// objeto Loja :)
var Loja = {};


/**
 * funcao maliciosa que configura os filtros da lista de produtos.
 * 
 * @author Carlos
 */
Loja.configuraFiltros = function()
{
    jQuery('.botao-filtro').click( 
        function(j)
        {
            jQuery('.botao-filtro').removeClass('selecionado');
            var e = jQuery(j.target); 
            jQuery(e).addClass('selecionado'); 
        });
        
    jQuery('.botao-filtro:first').removeClass('middle');   
    jQuery('.botao-filtro:first').addClass('left');
    jQuery('.botao-filtro:last').removeClass('middle');   
    jQuery('.botao-filtro:last').addClass('right');
}

Loja.configuraInicio = function(){
    console.log('passando aqui...');
    var alturaMenu = $('#left').height();
    var alturaContent = $('.left_content').height();
    if(alturaMenu > alturaContent){ 
        $('.left_content').height(alturaMenu+'px'); 
    }else{
        $('.left_content').height('100%'); 
    }
}

/**
 * Evento ready da pagina. 
 * As funcoes daqui são executadas quando a pagina estiver pronta.
 * 
 * @author Carlos
 */
jQuery(document).ready(
    function(){
        Loja.configuraFiltros();
        Loja.configuraInicio();
    });
