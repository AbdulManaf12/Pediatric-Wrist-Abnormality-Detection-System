#python static/yolov5/detect.py --data static/yolov5/dataset.yaml --device cpu --weights static/yolov5/best.pt --source input-image.png

import subprocess

def run_detection(weights, image):
    cmd = f"python static/yolov5/detect.py --data static/yolov5/dataset.yaml --device cpu --weights {weights} --source {image} --project static/"
    result = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    return result.stdout, result.stderr

def run():
    out, err = run_detection("static/yolov5/best.pt", "static/input-image.png")
    print("Output:", out)
    print("Error:", err)
