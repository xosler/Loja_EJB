/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function configuraFiltros()
{
    jQuery('[id*=panelFiltros] a').click( 
        function(j)
        {
            jQuery('[id*=panelFiltros] a').removeClass('selecionado');
            var e = jQuery(j.target); 
            jQuery(e).addClass('selecionado'); 
        } 
        );
}

jQuery(document).ready(
    function(){
        configuraFiltros();
    }
    );