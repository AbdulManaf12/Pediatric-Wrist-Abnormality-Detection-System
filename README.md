# Pediatric Wrist Abnormality Detection Syste

An application that detect anomalies in the X-Ray image with accuracy.

<img src="static/assets/gif/interface.gif" alt="X-ray image abnonormality detection" width="512">

## Introduction

The proposed project aims to provide a solution to the scarcity of radiologists and lack of specialized training among medical professionals in diagnosing and treating wrist abnormalities in children, adolescents, and young adults. With the incidence rate of distal radius and ulna fractures being higher during puberty, timely and accurate diagnosis is crucial. To address this, the project aims to provide an automated system using object detection algorithms and computer vision and machine learning techniques, enabling users to easily input pediatric wrist x-ray images and receive an output indicating the presence and location of any anomalies. The system is designed to be user-friendly, providing high accuracy and precision in diagnosis, and accessible through a mobile application without an internet connection. The project aims to reduce the dependency on manual interpretation of x-ray images, which can be time-consuming and subject to errors, and to make the diagnostic process more efficient for both patients and doctors. The research paper further highlights the effectiveness of single-stage deep neural network-based detection models, specifically YOLOv8x, in enhancing pediatric wrist imaging, with a fracture detection mean average precision (mAP) of 0.95 and an overall mAP of 0.77 on the GRAZPEDWRI-DX pediatric wrist dataset. In summary, the project aims to provide an innovative and efficient solution to improve the diagnosis and treatment of wrist abnormalities, ultimately improving patient care and convenience.

## Installation

## User Instruction

## Results

<details><summary>YOLOv8</summary>

| Model variants                                                                               | size<br><sup>(pixels) | Precision | Recall | mAP@0.5 | mAP@0.5:0.95 |
| ------------------------------------------------------------------------------------ | --------------------- | ----------|--------|---------|--------------|
| [YOLOv8n](https://github.com/AbdulManaf12/FYP-Project/releases/download/v0.0.0/YOLOv8n.pt) | 640             | 0.73 	   | 0.58 	| 0.59    | 0.36         |         
| [YOLOv8s](https://github.com/AbdulManaf12/FYP-Project/releases/download/v0.0.0/YOLOv8s.pt) | 640             | 0.72 	   | 0.63 	| 0.65    | 0.39         |     
| [YOLOv8m](https://github.com/AbdulManaf12/FYP-Project/releases/download/v0.0.0/YOLOv8m.pt) | 640             | 0.60 	   | 0.60 	| 0.56    | 0.36         |     
| [YOLOv8l](https://github.com/AbdulManaf12/FYP-Project/releases/download/v0.0.0/YOLOv8l.pt) | 640             | 0.74 	   | 0.60 	| 0.62    | 0.41         |     
| [YOLOv8x](https://github.com/AbdulManaf12/FYP-Project/releases/download/v0.0.0/YOLOv8x.pt) | 640             | 0.79 	   | 0.64 	| 0.77    | 0.53         |  

</details>

## Acknowledgement

## License

## Contact Info
