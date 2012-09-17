class Bowling:
    def calc(self, s):
        ROUND = 10
        score = 0
        frames = s.split('|')
        frames[10] = frames[11] if len(frames) > 10 else ''
        print frames
        for i in range(ROUND):
            if frames[i][0] == 'X':
                score += 10 + self.next2Balls(frames, i)
            elif frames[i][1] == '/':
                score += 10 + self.next1Balls(frames, i)
            else:
                first = self.conv(frames[i][0])
                second = self.conv(frames[i][1])
                score += first + second
        return score
     
    def next2Balls(self, frames, i):      
        r1 = self.conv(frames[i+1][0])
        if frames[i+1][0] == 'X':
            r2 = self.conv(frames[i+2][0])
        elif frames[i+1][1] == '/':
            r1 = 0
            r2 = 10
        else:
            r2 = self.conv(frames[i+1][1])
        return r1 + r2        

    def next1Balls(self, frames, i):
        return self.conv(frames[i+1][0])

    def conv(self, ball):
        if ball == 'X':
            r = 10
        elif ball == '-':
            r = 0
        else:
            r = int(ball)
        return r
    
'''
result = {
  'a': lambda x: x * 5,
  'b': lambda x: x + 7,
  'c': lambda x: x - 2
}[value](x)
'''