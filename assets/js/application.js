/******************************************
 *  jetbrick.widget.scrolltop.js
 ******************************************/
(function (root, $) {

    /**
     * 单击此处，返回到顶部
     */
    var scrolltop = function() {
        var $dom = $('<img src="/assets/images/scrolltop.png" />');
        $dom.css({
            display: 'none',
            cursor: 'pointer',
            position: 'fixed',
            right: '60px',
            bottom: '80px',
            border: '0 none',
            margin: 0,
            padding: 0,
            width: 'auto',
            height: 'auto'
        });
        $('body').append($dom);

        $dom.click(function() {
            $('body,html').animate({scrollTop:0}, 400);
            return false;
        });

        var hidden = true;
        $(window).on('scroll resize', function() {
            if ($(window).scrollTop() > 40) {
                if (hidden) {
                    $dom.fadeIn();
                    hidden = false;
                }
            } else {
                if (!hidden) {
                    $dom.fadeOut();
                    hidden = true;
                }
            }
        });
    };

    $(function() {
        scrolltop();
    });

})(typeof window == "undefined" ? window : this, jQuery);


