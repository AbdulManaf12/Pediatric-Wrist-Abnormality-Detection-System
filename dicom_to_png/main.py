from flask import Flask, request, render_template
from PIL import Image
import numpy as np
import pydicom
import os

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route("/start_converting", methods=['GET', 'POST'])
def start_converting():        
    if request.method == 'POST':
        remove_files()
        file = request.files.get('input-image')
        if file:
            file.save('static/convert-file.dicom')
            dicom_to_png('static/convert-file.dicom', 'static/convert-file.png')
    return render_template('index.html', file_path='static/convert-file.png')

def remove_files():
    if os.path.exists('static/convert-file.dicom'):
        os.remove('static/convert-file.dicom')
    if os.path.exists('static/convert-file.png'):
        os.remove('static/convert-file.png')

def dicom_to_png(input_file, output_file):
    im = pydicom.dcmread(input_file)
    im = im.pixel_array.astype(float)
    rescaled_image = (np.maximum(im, 0)/im.max()) * 255
    final_image = np.uint8(rescaled_image)
    final_image = Image.fromarray(final_image)
    final_image.save(output_file)


if __name__ == "__main__":
    app.run(debug=True) 
