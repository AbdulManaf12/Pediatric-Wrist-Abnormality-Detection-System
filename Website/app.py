from flask import Flask, request, render_template
from inference import run
from extract_bbox_info import yolo_to_dict
import os
import shutil
import datetime

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = './static'
app.config['SEND_FILE_MAX_AGE_DEFAULT'] = 0

@app.route("/")
def index():
    return render_template('index.html')

@app.route('/predict', methods=['GET', 'POST'])
def predict():
    # remove_files()
    if request.method == 'POST':
        patient_name = request.form['Name']
        age = request.form['Age']
        gender = request.form['Gender']
        result_type = request.form['ResultType']
        side = request.form['Side']
        projection = request.form['Projection']
        file = request.files.get('input-image')

        if file and allowed_file(file.filename):
            filename = f"{app.config['UPLOAD_FOLDER']}/input-image.png"
            file.save(filename)
        
        if result_type == 'detection':
            # run()
            now = datetime.datetime.now()
            # format the date string
            date_of_scan = now.strftime("%B %d, %Y")
    
            annotations = yolo_to_dict('static/exp/labels/input-image.txt', 'static/exp/input-image.png')

            # Render the medical report template with the data
            return render_template('result.html', 
                                patient_name=patient_name, 
                                age=age, 
                                gender=gender, result_type=result_type,
                                side=side,projection=projection,
                                date_of_scan=date_of_scan, 
                                annotations=annotations)

    return render_template('index.html')

def allowed_file(filename):
    ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def remove_files():
    if os.path.exists('static/exp'):
        shutil.rmtree('static/exp')
    if os.path.exists('input-image.png'):
        os.remove('input-image.png')


if __name__ == "__main__":
    app.run(debug=True)
