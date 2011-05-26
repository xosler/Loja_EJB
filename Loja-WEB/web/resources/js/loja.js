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

/**
 * Evento ready da pagina. 
 * As funcoes daqui são executadas quando a pagina estiver pronta.
 * 
 * @author Carlos
 */
jQuery(document).ready(
    function()
    {
        Loja.configuraFiltros();
    }
    );