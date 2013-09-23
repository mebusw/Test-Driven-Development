describe("Plot Model", function() {
  var model;
  
  beforeEach(function() {
    model = new PlotModel();
  });

  it("empty plot Model", function() {
    // player.play(song);
    // expect(player.currentlyPlayingSong).toEqual(song);

    // //demonstrates use of custom matcher
    // expect(player).toBePlaying(song);
    expect(model.points()).toEqual([]);
  });


  it("add points", function() {
    model.add(3, 5);
    model.add(6, 7);

    expect(model.points()).toEqual([ {x: 3, y: 5}, {x: 6, y: 7} ]);
  });

  it("remove points with default tolerance as 3px", function() {
    var tolerance = 3;  
    var y = 17;
    var yThatWithinTolerance = y + tolerance;
    model.add(3, 5);
    model.add(16, y);
    
    model.remove(16, yThatWithinTolerance);

    expect(model.points()).toEqual([ {x: 3, y: 5} ]);
  });    

  
  it("can not remove points that out of tolerance", function() {
    var tolerance = 1;  
    var y = 8;
    var yThatOutOfTolerance = y - (tolerance + 1);
    model.add(3, y);
    model.add(6, 7);

    model.remove(3, yThatOutOfTolerance, tolerance);

    expect(model.points()).toEqual([ {x: 3, y: y}, {x: 6, y: 7} ]);
  });      
});

///////////////////////////////////////
describe("Plot View", function() {
  var view;
  
  beforeEach(function() {
    view = new PlotView();
  });

  it("listener should receive addPointAt() triggered by View", function() {
    var self = this;
    self.addPointAt = jasmine.createSpy('presenter.addPointAt');
    view.registerListener(self);

    view.onButtonClickedWith(2, 5);
    
    expect(self.addPointAt).toHaveBeenCalledWith(2, 5);
  });

  it("listener should receive removePointAt() triggered by View", function() {
    var self = this;
    self.removePointAt = jasmine.createSpy('presenter.removePointAt');
    view.registerListener(self);

    view.onCanvasClickedAt(3, 6);
    
    expect(self.removePointAt).toHaveBeenCalledWith(3, 6);
  });

  it("canvas should send click() to View", function() {
    spyOn(view, "onCanvasClickedAt")
    var canvas = $('<canvas id="myCanvas" />');
    $("body").append(canvas);

    view.bindEvents();
    canvas.click();
    
    expect(view.onCanvasClickedAt).toHaveBeenCalled();
  });

  it("button should send click() to View", function() {
    spyOn(view, "onButtonClickedWith")
    var button = $('<input type="button" id="btnAdd" />');
    $("body").append(button);

    view.bindEvents();
    button.click();
    
    expect(view.onButtonClickedWith).toHaveBeenCalled();
  });
});

///////////////////////////////////
describe("Plot Presenter", function() {
  var presenter;
  var model;
  var view;
  
  beforeEach(function() {
    view = new PlotView();
    model = new PlotModel();
    presenter = new PlotPresenter(model, view);
  });

  it("Presenter should register self to view's listener, and also load model", function() {
    spyOn(view, "registerListener");

    presenter = new PlotPresenter(model, view);

    expect(presenter.view).toBeDefined();
    expect(presenter.model).toBeDefined();
    expect(view.registerListener).toHaveBeenCalledWith(presenter);
  });

  it("Presenter should update model and then view to add a point", function() {
    spyOn(view, "draw");
    spyOn(model, "add");

    presenter.addPointAt(1, 2);

    expect(model.add).toHaveBeenCalledWith(1, 2);
    expect(view.draw).toHaveBeenCalledWith(model);
  });

  it("Presenter should update model and then view to remove a point", function() {
    spyOn(view, "draw");
    spyOn(model, "remove");

    presenter.removePointAt(1, 2);

    expect(model.remove).toHaveBeenCalledWith(1, 2);
    expect(view.draw).toHaveBeenCalledWith(model);
  });
});
