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
    var alturaMenu = $('#left').height();
    var alturaContent = $('.left_content').height();
    if(alturaMenu > alturaContent){ 
        $('.left_content').height(alturaMenu+'px'); 
    }else{
        $('.left_content').height('100%'); 
    }
}


Loja.Paginas = {
    CAD_CATEGORIA: "cadastrarCategoria",
    CAD_PRODUTO: "cadastrarProduto",
    LIS_CATEGORIA: "listaCategorias",
    LIS_PRODUTO: "listaProdutos",
    INDEX: "index",
    COMPRAR: "comprar",
    DET_PRODUTO: "detalhesProduto"
};

Loja.facesFilter = ".xhtml";

Loja.gotopagina = function(pagina)
{
    window.location = pagina + Loja.facesFilter;
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
