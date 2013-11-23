<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title></title>

<!-- jQuery Mobile CSS -->
<link rel="stylesheet" href="${resource(dir: 'css/jquery', file: 'jquery.mobile-1.3.2.css')}">

<!-- Custom CSS -->
<link rel="stylesheet" href="${resource(dir: 'css/custom', file: 'custom.css')}">

<!-- jQuery and jQuery Mobile -->
<script src="${resource(dir: 'js/jquery', file: 'jquery-1.9.1.js')}"></script>
<script type="text/javascript">
    $(document).bind("mobileinit", function() {
        $.mobile.page.prototype.options.addBackBtn = true;
    });
</script>
<script src="${resource(dir: 'js/jquery', file: 'jquery.mobile-1.3.2.js')}"></script>