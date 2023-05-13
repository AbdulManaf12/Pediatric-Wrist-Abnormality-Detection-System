import os
import shutil
from flask import Flask, request, render_template
from ultralytics import YOLO

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/predict', methods=['GET', 'POST'])
def predict():
    remove_files()
    if request.method == 'POST':
        file = request.files.get('file')

        if file:
            filename = f"static/input-image.png"
            file.save(filename)
            os.chdir('static/')
            model = YOLO('YOLOv8x-best.pt')
            model('input-image.png', save=True)
            os.chdir('../')

        return render_template('result.html')
    return render_template('index.html')

def remove_files():
    if os.path.isdir('static/runs/'):
        shutil.rmtree('static/runs')
    if os.path.exists('static/input-image.png'):
        os.remove('static/input-image.png')

if __name__ == '__main__':
    # Run the Flask app
    app.run()
