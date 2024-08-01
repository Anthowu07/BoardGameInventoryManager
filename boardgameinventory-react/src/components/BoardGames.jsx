import React, { useEffect, useState } from 'react';
import { getBoardGames, createBoardGame } from '../services/boardgameApi';

const BoardGameList = () => {

    const [boardgames, setBoardGames] = useState([]);
    const [name, setName] = useState('');
    const [publisher, setPublisher] = useState('');
    const [reorder_quantity, setReorder_Quantity] = useState(0);
    const [error, setError] = useState(null);

    //Handle GET Request
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

    //Handle POST Request
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

    return (
        <div>
            <h1>Board Games!</h1>
            {error && <p>{error}</p>}
            <ul>
                {boardgames.map(boardgame => (
                    <li key={boardgames.id}>{boardgame.name} - {boardgame.publisher} - {boardgame.reorder_quantity}</li>
                ))}
            </ul>
            <h2>Add Board Game</h2>
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
            <button onClick={handleCreateBoardGame}>Create</button>
        </div>
    );

};



export default BoardGameList;