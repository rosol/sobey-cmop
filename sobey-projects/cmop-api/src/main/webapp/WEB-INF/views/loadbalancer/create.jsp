<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>

<html>
<head>
<title>Elb Create Demo</title>
</head>

<body>

	<form class="form-horizontal" role="form" method="post" action=".">

		<div class="page-header">
			<h3>创建ELB页面</h3>
		</div>

		<div class="form-group">
			<label for="vip" class="col-sm-2 control-label">VIP</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="vip" name="vip"
					placeholder="虚拟IP" value="10.10.2.51">
			</div>
		</div>

		<div class="form-group clone">
			<label for="publicIPs" class="col-sm-2 control-label">Ecs</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" name="publicIPs"
					placeholder="IP地址" value="10.10.100.1">
			</div>

			<div class="col-sm-2">
				<select name="protocols" class="form-control">
					<option value="HTTP">HTTP</option>
					<option value="SSL">HTTPS</option>
				</select>
			</div>

			<div class="col-sm-1">
				<button type="button" class="btn btn-default clone">Add</button>
			</div>
			<div class="col-sm-1">
				<button type="button" class="btn btn-warning clone">Delete
				</button>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</div>
	</form>

	<script>
		$(document).ready(function() {

			$(document).on("click", "button.clone", function() {
				var $this = $(this);
				var $div = $this.closest('div.clone');
				if ($this.hasClass("btn-warning")) {
					$div.remove();
				} else {
					var $clone = $div.clone();
					$clone.find('input[type=text]').val('');
					$div.after($clone);
				}
			});

		});
	</script>

</body>
</html>
