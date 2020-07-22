
(function (any) {
    'use strict';
    any(window)['on']('load', function () {
        any('[data-loader="circle-side"]')['fadeOut']();
        any('#preloader')['delay'](350)['fadeOut']('slow');
        any('body')['delay'](350);
        any('header, #hero_in h1, #hero_in form')['addClass']('animated');
        any('.hero_single, #hero_in')['addClass']('start_bg_zoom');
        any(window)['scroll']()
    });


    any(window)['on']('scroll', function () {
        if (any(this)['scrollTop']() > 1) {
            any('.header')['addClass']('sticky')
        } else {
            any('.header')['removeClass']('sticky')
        }
    });

    var va = new WOW({
        boxClass: 'wow',
        animateClass: 'animated',
        offset: 0,
        mobile: true,
        live: true,
        scrollContainer: null
    });

    va['init']();
    any('.video')['magnificPopup']({
        type: 'iframe'
    });

})(window['jQuery'])



