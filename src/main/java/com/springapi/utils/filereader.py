import os,sys

def readFile(path):
    contents = []
    path = path if os.path.exists(path) else ""
    with open(path) as f:
        print(f)
        for log in f.read():
            contents.append(log)
    print(contents)
    return contents

if __name__ == "__main__":
    path = "/Users/derryspann/Desktop/Dev/Java/projects/springthyme/logs/com.springapi.log"
    # print(os.path.exists(path))
    readFile(path)