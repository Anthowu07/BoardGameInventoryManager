import React, { useEffect, useState } from 'react';
import { getBoardGames, createBoardGame, deleteBoardGame, updateBoardGame } from '../services/boardgameApi';
import './BoardGames.css';

const BoardGameList = () => {
    const [boardgames, setBoardGames] = useState([]);
    const [name, setName] = useState('');
    const [publisher, setPublisher] = useState('');
    const [reorder_quantity, setReorder_Quantity] = useState('');
    const [error, setError] = useState(null);
    const [editing, setEditing] = useState(false);
    const [currentBoardGame, setCurrentBoardGame] = useState({});

    // Handle GET Request
    useEffect(() => {
        const fetchBoardGames = async () => {
            try {
                const data = await getBoardGames();
                setBoardGames(data);
            } catch (error) {
                setError(error.message);
            }
        };

        fetchBoardGames();
    }, []);

    // Handle POST Request
    const handleCreateBoardGame = async () => {
        const newBoardGame = { name, publisher, reorder_quantity };
        try {
            await createBoardGame(newBoardGame);
            const data = await getBoardGames();
            setBoardGames(data);
        } catch (error) {
            setError(error.message);
        }
    };

    // Handle DELETE Request
    const handleDeleteBoardGame = async (boardgame_id) => {
        console.log('Deleting board game with id:', boardgame_id); // Log the id
        try {
            await deleteBoardGame(boardgame_id);
            const data = await getBoardGames();
            setBoardGames(data);
        } catch (error) {
            setError(error.message);
        }
    };

    // Handle EDIT Request
    const handleEditBoardGame = async (boardgame) => {
        setEditing(true);
        setCurrentBoardGame(boardgame);
        setName(boardgame.name);
        setPublisher(boardgame.publisher);
        setReorder_Quantity(boardgame.reorder_quantity);
    };

    const handleUpdateBoardGame = async () => {
        const updatedBoardGame = { ...currentBoardGame, name, publisher, reorder_quantity };
        try {
            await updateBoardGame(updatedBoardGame);
            const data = await getBoardGames();
            setBoardGames(data);
            setEditing(false);
            setCurrentBoardGame({});
            setName('');
            setPublisher('');
            setReorder_Quantity(0);
        } catch (error) {
            setError(error.message);
        }
    };

    return (
        <div>
            {error && <p>{error}</p>}
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Publisher</th>
                        <th>Reorder Quantity</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {boardgames.map(boardgame => (
                        <tr key={boardgame.boardgame_id}>
                            <td>{boardgame.name}</td>
                            <td>{boardgame.publisher}</td>
                            <td>{boardgame.reorder_quantity}</td>
                            <td>
                                <button onClick={() => handleDeleteBoardGame(boardgame.boardgame_id)}>Delete</button>
                                <button onClick={() => handleEditBoardGame(boardgame)}>Edit</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <h2>{editing ? 'Edit Board Game' : 'Add Board Game'}</h2>
            <input
                type="text"
                placeholder="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />
            <input
                type="text"
                placeholder="Publisher"
                value={publisher}
                onChange={(e) => setPublisher(e.target.value)}
            />
            <input
                type="number"
                placeholder="Reorder Quantity"
                value={reorder_quantity}
                onChange={(e) => setReorder_Quantity(e.target.value)}
            />
            <button onClick={editing ? handleUpdateBoardGame : handleCreateBoardGame}>
                {editing ? 'Update' : 'Add'}
            </button>
        </div>
    );
};

export default BoardGameList;
