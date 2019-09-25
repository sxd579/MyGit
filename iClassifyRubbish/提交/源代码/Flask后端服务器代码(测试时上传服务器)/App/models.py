# coding=utf-8，一定要在最顶行添加
from App.ext import db


class Questions(db.Model):
    id = db.Column(db.Integer, primary_key=True, autoincrement=True, nullable=False)
    q_content = db.Column(db.String(10), nullable=False)
    q_A = db.Column(db.Boolean, nullable=False)
    q_B = db.Column(db.Boolean, nullable=False)
    q_C = db.Column(db.Boolean, nullable=False)
    q_D = db.Column(db.Boolean, nullable=False)
    q_answer = db.Column(db.String(10), nullable=False)
    q_selection = db.Column(db.String(10))


class Rubbish(db.Model):
    id = db.Column(db.Integer, primary_key=True, autoincrement=True, nullable=False)
    r_Name = db.Column(db.String(10), nullable=False)
    r_Type = db.Column(db.String(10), nullable=False)
