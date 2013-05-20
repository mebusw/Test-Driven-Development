	it("bowling", function() {
	    var next2Balls = function(frames, i) {
	        r1 = conv(frames[i+1][0])
	        if (frames[i+1][0] === 'X') {
	            r2 = conv(frames[i+2][0])
	        }
	        else if (frames[i+1][1] === '/') {
	            r1 = 0
	            r2 = 10
	        } else {
	            r2 = conv(frames[i+1][1])
	        }
	        return r1 + r2        
	    }
	    var next1Balls = function(frames, i) {
	        return conv(frames[i+1][0])
	    }
	        
		var conv = function(ball) {
	        if (ball === 'X')
	            r = 10
	        else if (ball === '-')
	            r = 0
	        else
	            r = parseInt(ball)
	        return r
		}
		var calc = function(s) {
	        var ROUND = 10
	        var score = 0
	        var frames = s.split('|')
	        console.log(frames)
	        if (frames.length > 10)
	        	frames[10] = frames[11] 
	        //print frames
	        for (var i=0; i < ROUND; i++) {
	            if (frames[i][0] === 'X') {
	                score += 10 + next2Balls(frames, i)
	            } else if (frames[i][1] === '/') {
	                score += 10 + next1Balls(frames, i)
	          	} else {
	                first = conv(frames[i][0])
	                second = conv(frames[i][1])
	                score += first + second
	        	}
	        }
	        return score
		}
		expect(calc('9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||')).toEqual(90)
		expect(calc('81|81|81|81|81|81|81|81|81|81||')).toEqual(90)
		expect(calc('-4|-4|-4|-4|-4|-4|-4|-4|-4|-4||')).toEqual(40)
		expect(calc('X|X|X|X|X|X|X|X|X|X||XX')).toEqual(300)
		expect(calc('5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5')).toEqual(150)
		
		
	});