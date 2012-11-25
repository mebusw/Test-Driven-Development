/*
 * gameoflife
 * https://github.com/mebusw/Test-Driven-Development
 *
 * Copyright (c) 2012 mebusw
 * Licensed under the MIT license.
 */

var Game = function() {
    return {
        tick: function (grid) {
            var len = grid.length;
            var result = this._arrayGen(0, len);
            for (var i = 1; i < len - 1; i++) {
                if(grid[i] ===1 && grid[i - 1] === 1 && grid[i + 1] === 1) {
                    result[i] = 1;
                }
                
            }
            return result;
        },
        
        _arrayGen: function(elem, len) {
            var arr = [];
            for(var i = 0; i < len; i++) {
                arr.push(elem);
            }
            return arr;
        },
    };
    
}