/* TODO
- add points with inputed x/y
- remove points
- MVP (html5 as View)
- testP
- testV

*/

///////////////////////////////
function PlotModel() {
	this._points = []
}

PlotModel.prototype.points = function() {
  return this._points;
};

PlotModel.prototype.add = function(x, y) {
  this._points.push({x: x, y: y});
};

PlotModel.prototype.remove = function(x, y, tolerance) {
	var tolerance = tolerance ? tolerance : 3;
	for (var i = 0; i < this._points.length; i++) {
		if (Math.abs(this._points[i].x - x) <= tolerance && Math.abs(this._points[i].y - y) <= tolerance) {
			this._points.splice(i, 1);
			break;
		}
	}
  	return;
};

////////////////////////////////

function PlotView() {
	this.bindEvents();
}

PlotView.prototype.registerListener = function(listener) {
  this._listener = listener;
};

PlotView.prototype.onCanvasClickedAt = function(x, y) {
  this._listener.removePointAt(x, y);
};

PlotView.prototype.onButtonClickedWith = function(x, y) {
  this._listener.addPointAt(x, y);
};

PlotView.prototype.bindEvents = function() {
	var self = this;
	$("#myCanvas").click(function(e) {
	    var x = e.pageX - $("#myCanvas").offset().left;
	    var y = e.pageY - $("#myCanvas").offset().top;
		console.log(e, x, y);
	    self.onCanvasClickedAt(x, y);
	});
	$("#btnAdd").click(function(e) {
		console.log(e);
		var x = $("#x").val();
		var y = $("#y").val();
	    self.onButtonClickedWith(x, y);
	});	
};

PlotView.prototype.draw = function(model) {
  	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");

	ctx.clearRect(0, 0, c.width, c.height);

	var points = model.points();
	for (var i = 0; i < points.length; i++) {
		var p = points[i];

		if (i !== 0) {
			ctx.lineTo(p.x ,p.y);
			ctx.stroke();
		}
		ctx.beginPath();
		ctx.arc(p.x, p.y, 10, 0, 2 * Math.PI);
		ctx.stroke();
		ctx.moveTo(p.x, p.y);
	}
};

////////////////////////////////

function PlotPresenter(model, view) {
	this.model = model;
	this.view = view;
	view.registerListener(this);
}

PlotPresenter.prototype.addPointAt = function(x, y) {
	this.model.add(x, y);
	this.view.draw(this.model);
};

PlotPresenter.prototype.removePointAt = function(x, y) {
	this.model.remove(x, y);
	this.view.draw(this.model);
};